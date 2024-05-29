using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ASD;
public class HashTableChainMethod
{
    private readonly int _size;
    private readonly List<PersonModel>?[] _data;
    public HashTableChainMethod(int size)
    {
        _size = size;
        _data = new List<PersonModel>?[_size];
    }

    public void Print()
    {
        int i = 1;
        foreach (var element in _data)
        {
            if (element == null)
            {
                Console.WriteLine($"Элемент {i} - null");
            }
            else
            {
                Console.Write($"Элемент {i} -");
                foreach (var item in element)
                {
                    Console.Write($"| {item.Name} {item.Age} |");
                }
            Console.WriteLine();
            }
            i++;
        }
    }

    public void Insert(PersonModel personModel)
    {
        int index = GetIndex(personModel);

        if (_data[index] == null)
        {
            _data[index] = new List<PersonModel> { personModel };
            return;
        }

        _data[index].Add(personModel);
    }

    public void Delete(PersonModel personModel)
    {
        int index = GetIndex(personModel);

        if(_data[index].Count == 1)
        {
            _data[index] = null;
            return;
        }

        foreach (var item in _data[index])
        {
            if (item.Age == personModel.Age && item.Name == personModel.Name)
            {
                _data[index].Remove(item);
                return;
            }
        }
    }

    public bool Find(PersonModel personModel)
    {
        int index = GetIndex(personModel);

        if (_data[index] == null)
        {
            return false;
        }
        else
        {
            foreach (var item in _data[index])
            {
                if (item.Age == personModel.Age && item.Name == personModel.Name)
                {
                    return true;
                }
            }

            return false;
        }
    }

    private int GetIndex(PersonModel personModel)
        => (personModel.Age * personModel.Age + personModel.Name.Length * personModel.Name.Length + personModel.Age * personModel.Name.Length) % _size;

}
